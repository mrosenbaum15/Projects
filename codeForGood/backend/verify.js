if (process.env.NODE_ENV !== 'production') require('dotenv').config();

const { ACCOUNT_SID, AUTH_TOKEN, SERVICE_SID } = process.env;
const client = require('twilio')(ACCOUNT_SID, AUTH_TOKEN);

// channel is sms or phone
const startVerify = async (to, channel) => {
  console.log({ to, channel });
  try {
    return client.verify
      .services(SERVICE_SID)
      .verifications.create({ to, channel: channel });
  } catch (err) {
    console.error(err);
  }
};

const endVerify = async (to, code) => {
  try {
    return client.verify
      .services(SERVICE_SID)
      .verificationChecks.create({ to, code });
  } catch (err) {
    console.error(err);
  }
};

module.exports = { startVerify, endVerify };
