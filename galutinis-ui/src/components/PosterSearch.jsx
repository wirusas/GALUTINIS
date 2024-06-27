import React, { useState } from 'react';

function PosterSearch() {
    const [posterName, setPosterName] = useState('');
    const [searchResults, setSearchResults] = useState([]);
    const [error, setError] = useState('');

    const handleSearch = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch(`/api/posters/posterName?posterName=${posterName}`);
            if (!response.ok) {
                throw new Error('Failed to fetch posters');
            }
            const data = await response.json();
            setSearchResults(data);
            setError('');
        } catch (error) {
            setError('Failed to fetch posters');
            setSearchResults([]);
        }
    };

    return (
        <div>
            <h2>Find Poster by Name</h2>
            <form onSubmit={handleSearch}>
                <label htmlFor="posterName">Enter Poster Name:</label>
                <input
                    type="text"
                    id="posterName"
                    value={posterName}
                    onChange={(e) => setPosterName(e.target.value)}
                    required
                />
                <button type="submit">Search</button>
            </form>
            {error && <p>{error}</p>}
            <div>
                {searchResults.length > 0 ? (
                    <ul>
                        {searchResults.map((poster) => (
                            <li key={poster.id}>{`${poster.posterName} - ${poster.category}`}</li>
                        ))}
                    </ul>
                ) : (
                    <p>No posters found.</p>
                )}
            </div>
        </div>
    );
}

export default PosterSearch;
