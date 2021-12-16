import axios from 'axios'

export default function initForm(form, onSuccess, onError) {
  form.onsubmit = handleForm(onSuccess, onError)
  document
    .querySelectorAll('form')
    .forEach(form => (form.onsubmit = handleForm))
}

function handleForm(onSuccess, onError) {
  return event => {
    event.preventDefault()
    const inputs = event.currentTarget.querySelectorAll('input')
    const data = {}

    for (const input of inputs) {
      data[input.name] = input.type === 'checkbox' ? input.checked : input.value
    }

    axios.request({
      method: event.currentTarget.method || 'post',
      url: event.currentTarget.action,
      data
    }).then(onSuccess).catch(onError)
  }
}
