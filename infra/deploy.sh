#!/bin/bash

echo "🚀 Aplicando manifests da pasta k8s..."

kubectl apply -f ./k8s --recursive

echo "✅ Tudo aplicado com sucesso!"