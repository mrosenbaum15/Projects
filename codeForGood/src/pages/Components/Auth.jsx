import React, { useState } from 'react';

import Logo from '../../../logo1.jpg';

import GradeDisplay from './GradeDisplay';
// import 'regenerator-runtime/runtime';

export default function Auth() {
  const [phNumber, setPhNumber] = useState('');
  const [pendingCode, setPendingCode] = useState('');
  const [revealPendingCode, setRevealPendingCode] = useState(false);
  const [authenticated, setAuthenticated] = useState(false);

  const handleSubmitPress = async event => {
    event.preventDefault();
    console.log(phNumber, pendingCode);
    if (pendingCode) {
      // second verify step
      const rtn = await (await fetch(
        `http://localhost:3000/endVerify?to=${phNumber}&code=${pendingCode}`,
      )).json();

      console.log(rtn);
      if (rtn.status === 'approved') setAuthenticated(true);
    } else {
      // first verify step

      await (await fetch(
        `http://localhost:3000/startVerify?to=${phNumber}`,
      )).json();

      setRevealPendingCode(true);
    }
  };

  return (
    <div>
      {' '}
      {!authenticated ? (
        <form className="login100-form validate-form">
          <span className="login100-form-title p-b-48">
            <a href="https://poderworks.org">
              <img src={Logo}></img>
            </a>
          </span>
          <span
            style={{ marginTop: '-35px' }}
            className="login100-form-title p-b-26"
          >
            Login
          </span>
          {!revealPendingCode ? (
            <input
              value={phNumber}
              onChange={e => setPhNumber(e.target.value)}
              type="tel"
              placeholder="phone number"
            />
          ) : (
            <input
              value={pendingCode}
              onChange={e => setPendingCode(e.target.value)}
              type="text"
              placeholder="pending code"
            />
          )}
          <button onClick={handleSubmitPress}>Submit</button>
        </form>
      ) : null}
      {authenticated ? <GradeDisplay phone={phNumber} /> : null}
    </div>
  );
}
