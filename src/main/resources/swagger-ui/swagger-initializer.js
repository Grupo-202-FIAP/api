window.onload = function() {
  window.ui = SwaggerUIBundle({
    url: "/v3/api-docs", // Seu endpoint OpenAPI
    dom_id: '#swagger-ui',
    deepLinking: true,
    presets: [
      SwaggerUIBundle.presets.apis,
      SwaggerUIStandalonePreset
    ],
    plugins: [
      SwaggerUIBundle.plugins.DownloadUrl
    ],
    layout: "StandaloneLayout"
  });
};