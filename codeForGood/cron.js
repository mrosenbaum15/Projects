const firebase = require('firebase');
const faker = require('faker');

// these credentials have been revoked!
firebase.initializeApp({
  apiKey: 'AIzaSyBW7RjQA3076jmwlILkGxUply9nL-C_r0c',
  authDomain: 'code4good2k19.firebaseapp.com',
  databaseURL: 'https://code4good2k19.firebaseio.com',
  projectId: 'code4good2k19',
  storageBucket: 'code4good2k19.appspot.com',
  messagingSenderId: '876647270690',
  appId: '1:876647270690:web:d715002170e2b6eecabd7b',
});

const database = firebase.database();

const genUser = () => {
  const rtn = {
    email: faker.internet.email(),
    name: faker.name.findName(),
    phone: faker.phone.phoneNumberFormat(),
    placement: Math.floor(Math.random() * 100),
    currentClass: 'Venture Basic',
    VentureBasic: {
      attendance: Math.floor(Math.random() * 9),
      hw1: Math.floor(Math.random() * 10),
      hw2: Math.floor(Math.random() * 10),
      hw3: Math.floor(Math.random() * 10),
      final: Math.floor(Math.random() * 20),
    },
    address: {
      street: faker.address.streetAddress(),
      city: faker.address.city(),
      zip: faker.address.zipCode(),
      state: faker.address.state(),
    },
  };

  return rtn;
};

const users = [];
console.log(genUser());
for (let i = 0; i < 100; i++) {
  users.push(genUser());
}

database.ref('allUsers/').set(users);
