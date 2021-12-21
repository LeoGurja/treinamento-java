import 'regenerator-runtime/runtime'
import { Grid, html } from 'gridjs'
import axios from 'axios'
import 'gridjs/dist/theme/mermaid.css'

export const gridHtml = html

export default async function grid(elemento, columns, url, requestHandler) {
  const language = await getLanguageObj()
  return new Grid({
    language,
    columns,
    server: {
      url,
      then: requestHandler
    }
  }).render(elemento)
}

async function getLanguageObj() {
  let cachedRequest = JSON.parse(localStorage.getItem('gridLanguageObj'))
  if (!cachedRequest) {
    cachedRequest = (await axios.get('/api/lang/grid')).data
    localStorage.setItem('gridLanguageObj', JSON.stringify(cachedRequest))
  }

  return {
    search: {
      placeholder: cachedRequest.searchPlaceholder
    },
    pagination: {
      previous: cachedRequest.paginationPrevious,
      next: cachedRequest.paginationNext,
      showing: cachedRequest.paginationShowing,
      results: cachedRequest.paginationRecords
    }
  }
}
