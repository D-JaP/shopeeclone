# Use the official PostgreSQL image as the base
FROM postgres:latest

# Set environment variables (optional)
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=root
ENV POSTGRES_DB=shopee_dB
ENV PGPORT=5433

# Copy initialization scripts (optional)
COPY /. /docker-entrypoint-initdb.d/
