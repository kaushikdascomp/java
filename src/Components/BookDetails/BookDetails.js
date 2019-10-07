import React from 'react';

const BookDetails = ({ bookDetails }) => {
    return (
      <div>
        <center><h1>Book Details</h1></center>
       
        {bookDetails.map((book) => (
            
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">{book.publisher}</h5>
              <h6 class="card-subtitle mb-2 text-muted">{book.author}</h6>
              <p class="card-text">{book.language}</p>
            </div>
          </div>
        ))}
      </div>
    )
  };
export default BookDetails;