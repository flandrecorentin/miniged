FROM node:lts-alpine
RUN npm install -g serve
WORKDIR /frontend
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build && npm i vue-router@next
EXPOSE 3000
CMD [ "serve", "-s" ,"/frontend/dist" ]