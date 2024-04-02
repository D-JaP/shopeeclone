# docker file for deploy react app
FROM node:16.20.2-alpine as build

WORKDIR /app

COPY package*.json ./
COPY *.json ./
COPY .env ./
COPY . ./

RUN npm install

# include env file to app


RUN npm run build

FROM nginx:alpine
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app/build /usr/share/nginx/html
COPY nginx.conf /etc/nginx/nginx.conf
ENV PORT=80
EXPOSE 80
RUN chown root /usr/share/nginx/html/*
CMD ["nginx", "-g", "daemon off;"]
