#!/bin/bash

print_message() {
  echo -e "\n----------------------------------------"
  echo "$1"
  echo "----------------------------------------"
}

spinner() {
  local frames=("⠋" "⠙" "⠹" "⠸" "⠼" "⠴" "⠦" "⠧" "⠇" "⠏")
  local pid=$1
  local i=0
  tput civis
  while kill -0 "$pid" 2>/dev/null; do
    printf "\r⏳ ${frames[i]} Processando..."
    i=$(( (i+1) % ${#frames[@]} ))
    sleep 0.1
  done
  tput cnorm
  printf "\r✅ Finalizado com sucesso!        \n"
}


start_with_spinner() {
  print_message "Iniciando o processo do Docker Compose..."
  docker-compose pull &
  spinner $!

  docker-compose up -d --build &
  spinner $!

  if [ $? -eq 0 ]; then
    print_message "Docker Compose executado com sucesso! ✅"
  else
    print_message "❌ Erro ao executar o Docker Compose!"
    exit 1
  fi
}

wait_for_health() {
  local container=$1
  local retries=20
  local count=0

  print_message "Verificando saúde do container: $container"
  while true; do
    status=$(docker inspect --format '{{.State.Health.Status}}' "$container" 2>/dev/null)
    echo "⏱  [$container] Status atual: $status"
    if [ "$status" == "healthy" ]; then
      echo "✅ [$container] está saudável!"
      break
    fi
    sleep 2
    ((count++))
    if [ $count -ge $retries ]; then
      print_message "❌ Timeout: container $container não ficou saudável a tempo."
      exit 1
    fi
  done
}

# EXECUÇÃO
start_with_spinner
wait_for_health "prometheus"
wait_for_health "grafana"
wait_for_health "loki"
wait_for_health "postgres"
wait_for_health "app"

print_message "Todos os serviços estão funcionando corretamente! ✅"