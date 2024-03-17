# Use the official PostgreSQL image as the base
FROM postgres:latest

# Set environment variables (optional)
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=root
ENV POSTGRES_DB=product_db

# Copy initialization scripts (optional)
COPY /. /docker-entrypoint-initdb.d/


RUN ls -R /docker-entrypoint-initdb.d/
