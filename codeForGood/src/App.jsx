import React from 'react';
import { Route, Switch } from 'react-router-dom';
import Form from './pages/Form';
import Grades from './pages/Grades';
import Home from './pages/Home';

export default function App() {
  return (
    <Switch>
      <Route exact path="/" component={Home} />
      <Route path="/form" component={Form} />
      <Route path="/grades" component={Grades} />
    </Switch>
  );
}
