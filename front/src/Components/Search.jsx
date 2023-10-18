import '../Styles/Search.css'

const Search = () => {
  return (
        <form className="search-form" action="#" method="GET">
            <input className="input-search" type="text" id="location" name="location" placeholder='&#x1F50E; Selecciona tu ubicación' />
            <input className="input-search" type="text" id="date" name="date" placeholder='&#x1F4C5; Elige una fecha' />
            <button className="primary-button search-button primary-text-button" type="submit">Consultar disponibilidad</button>
        </form>
  )
}

export default Search
