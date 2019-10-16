const Koa = require('koa');
const Router = require('@koa/router');
const cors = require('@koa/cors');

const app = new Koa();
const router = new Router();
app.use(
  cors({
    origin: '*',
  }),
);

const { startVerify, endVerify } = require('./verify');

router.get('/startVerify', async (ctx, next) => {
  //   console.log(query);
  ctx.body = await startVerify(`+1${ctx.request.query.to}`, 'sms');
});

router.get('/endVerify', async (ctx, next) => {
  const { to, code } = ctx.request.query;
  ctx.body = await endVerify(`+1${to}`, code);
});

app.use(router.routes()).use(router.allowedMethods());

app.listen(3000);
