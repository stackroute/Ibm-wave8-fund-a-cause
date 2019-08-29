FROM node
WORKDIR /app
COPY . .
RUN npm install && \
   npm run build
EXPOSE 4200
ENTRYPOINT [ "npm", "start" ]
#COPY --from=builder /app/dist/* /usr/share/nginx/html/