import React, {Component} from 'react';
import './App.css';
import BookDetails from './Components/BookDetails/BookDetails';


class App extends Component{
  state = {
    bookDetails: [
      
       
    ]
    
  };

  componentDidMount() {
    fetch('http://localhost:9090/library/api/getAllBooks/1' ,{
      method:'GET',
      headers:{
        'Access-Control-Allow-Origin':'*'       
      }}    
    )
    .then(res => res.json())
    .then((data) => {
      this.setState({ bookDetails: data })
      console.log(this.state.bookDetails)
    })
    .catch(console.log)
  }



  render(){
    return(
      <BookDetails bookDetails={this.state.bookDetails} />
    )
  }

}

export default App;
