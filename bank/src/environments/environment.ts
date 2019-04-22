// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,
  url_base: 'http://127.0.0.1:8080/',
  HTTP_OK: '200',
  HTTP_BAD_REQUEST: '400',
  HTTP_NOT_FOUND: '404'
};
