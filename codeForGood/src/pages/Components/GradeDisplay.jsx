import React, { useState } from 'react';
import firebase from 'firebase';
firebase.initializeApp({
  databaseURL: 'https://code4good2k19.firebaseio.com',
});

const getFromFirebase = phone => {
  return firebase.database().ref('allUsers/0');
};

export default function GradeDisplay({ phone }) {
  console.log(phone);
  const [person, setPerson] = useState({ VentureBasic: {} });

  const getFromFirebase = () => {
    return firebase
      .database()
      .ref('allUsers')
      .orderByChild('phone')
      .equalTo(phone)
      .on('child_added', val => {
        // console.log(val.toJSON());
        setPerson(val.toJSON());
      });
  };
  getFromFirebase(phone);
  return (
    <div>
      <table id="grades">
        <tbody>
          <tr>
            <th>Assignment</th>
            <th>Grade</th>
          </tr>

          {Object.entries(person.VentureBasic).map(
            ([assignment, grade], idx) => {
              return (
                <tr key={idx}>
                  <td>{assignment}</td>
                  <td>{grade}</td>
                </tr>
              );
            },
          )}
        </tbody>
      </table>

      <table id="attendance"></table>
    </div>
  );
}
