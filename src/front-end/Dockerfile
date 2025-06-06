FROM node:23-slim AS base

ARG PORT=3000

ENV NEXT_TELEMETRY_DISABLED=1

WORKDIR /logblock

# deps installing
FROM base AS dependencies

COPY package.json package-lock.json ./
RUN npm ci

# Building
FROM base AS build

COPY --from=dependencies /logblock/node_modules ./node_modules
COPY . .

RUN npm run build

# Production
FROM base AS run

ENV NODE_ENV=production
ENV PORT=$PORT

RUN addgroup --system --gid 1001 nodejs
RUN adduser --system --uid 1001 logblock-fe
RUN mkdir .next
RUN chown logblock-fe:nodejs .next

COPY --from=build /logblock/public ./public
COPY --from=build --chown=logblock-fe:nodejs /logblock/.next/standalone ./
COPY --from=build --chown=logblock-fe:nodejs /logblock/.next/static ./.next/static

USER logblock-fe

EXPOSE $PORT

ENV HOSTNAME="0.0.0.0"
CMD ["node", "server.js"]