@REM docker tag shopeeclone-authservice-database:latest harry2108/shopeeclone-authservice-database:latest
@REM docker push harry2108/shopeeclone-authservice-database:latest

@REM docker tag shopeeclone-productservice-postgres-db:latest harry2108/shopeeclone-productservice-postgres-db:latest
@REM docker push harry2108/shopeeclone-productservice-postgres-db:latest

docker build ./Backend/AuthService -t shopeeclone-authservice:latest
docker tag shopeeclone-authservice:latest harry2108/shopeeclone-authservice:latest
docker push harry2108/shopeeclone-authservice:latest

docker build ./Backend/ApiGateway -t shopeeclone-apigateway:latest
docker tag shopeeclone-apigateway:latest harry2108/shopeeclone-apigateway:latest
docker push harry2108/shopeeclone-apigateway:latest

docker build ./Backend/ProductService -t shopeeclone-productservice:latest
docker tag shopeeclone-productservice:latest harry2108/shopeeclone-productservice:latest
docker push harry2108/shopeeclone-productservice:latest

docker build ./Frontend -t harry2108/shopeeclone-frontend:latest
docker tag shopeeclone-frontend:latest shopeeclone-frontend:latest
docker push harry2108/shopeeclone-frontend:latest

