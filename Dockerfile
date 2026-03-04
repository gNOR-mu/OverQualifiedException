# Se requiere una imagen de Postgres con 15 años de experiencia en bases de datos, pero que esté dispuesta a cobrar los recursos de SQLite.
FROM postgres:15-alpine

# Variables de entorno por defecto (El salario mínimo emocional del mercado)
ENV POSTGRES_USER=junior_developer
ENV POSTGRES_PASSWORD=unpaid_internship
ENV POSTGRES_DB=job_market_db

# Exponemos el puerto, aunque probablemente los reclutadores ignoren tu currículum de todas formas
EXPOSE 5432
