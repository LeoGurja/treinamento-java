import grid, { gridHtml } from '../grid'

grid(
  document.querySelector('.gridjs'),
  ['Nome', 'CPF', 'Email', 'Ações'],
  '/api/alunos',
  data => data.map(aluno => [
    gridHtml(`<a href="/alunos/${aluno.id}">${aluno.nome}</a>`),
    formataCpf(aluno.cpf),
    aluno.email,
    gridHtml(montaAcoes(aluno.id))
  ])
)

function formataCpf(cpf) {
  return `${cpf.slice(0, 3)}.${cpf.slice(3, 6)}.${cpf.slice(6, 9)}-${cpf.slice(9)}`
}

function montaAcoes(id) {
  return `
    <span class="d-flex justify-content-evenly">
      <a href="/alunos/${id}" class="btn btn-info">Detalhar</a>
      <a href="/alunos/${id}/edit" class="btn btn-warning">Editar</a>
      <form action="/alunos/${id}" method="delete">
        <button class="btn btn-danger">Deletar</a>
      </form>
    </span>
  `
}
