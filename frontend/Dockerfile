FROM node:13.12.0-alpine

RUN mkdir /home/frontend
WORKDIR /home/frontend

ARG PROXY_URL
ENV PROXY_URL ${PROXY_URL}
ENV PATH /home/frontend/node_modules/.bin:$PATH

COPY . /home/frontend

RUN sed -i 's#http://localhost:8080#'"${PROXY_URL}"'#' /home/frontend/package.json
RUN npm install --silent
RUN npm install react-scripts@3.4.1 -g --silent

CMD ["npm", "start"]
