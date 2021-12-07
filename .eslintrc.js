module.exports = {
  env: {
    browser: true,
    es2021: true
  },
  extends: [
    'plugin:promise/recommended',
    'standard'
  ],
  plugins: [
    'prefer-arrow',
    'promise',
    'prettier'
  ],
  parserOptions: {
    ecmaVersion: 12,
    sourceType: 'module'
  },
  rules: {
    'space-before-function-paren': ['error', 'never'],
    'prefer-arrow/prefer-arrow-functions': [
      'warn',
      {
        disallowPrototype: true,
        singleReturnOnly: false,
        classPropertiesAllowed: false,
        allowStandaloneDeclarations: true
      }
    ],
    'arrow-body-style': ['error', 'as-needed']
  }
}
