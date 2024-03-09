INSERT INTO oauth_client_details(
	CLIENT_ID,
	RESOURCE_IDS,
	CLIENT_SECRET,
	SCOPE,
	AUTHORIZED_GRANT_TYPES,
	AUTHORITIES,
	ACCESS_TOKEN_VALIDITY,
	REFRESH_TOKEN_VALIDITY
) VALUES (
	'base-app-mobile',
	'base-app-resource',
	'$2a$10$gfBHWYa6muZQJEsxgJchvu5gJZPV4lmUoMPGxr0SoRZypMfv86iMC',
	'read,write',
	'password,refresh_token',
	'ADMIN,TRUSTED_CLIENT',
	3600,
	2592000
);

INSERT INTO oauth_client_details(
	CLIENT_ID,
	RESOURCE_IDS,
	CLIENT_SECRET,
	SCOPE,
	AUTHORIZED_GRANT_TYPES,
	AUTHORITIES,
	ACCESS_TOKEN_VALIDITY,
	REFRESH_TOKEN_VALIDITY
) VALUES (
	'base-app-mobile-dev',
	'base-app-resource',
	'$2a$10$gfBHWYa6muZQJEsxgJchvu5gJZPV4lmUoMPGxr0SoRZypMfv86iMC',
	'read,write',
	'password,refresh_token',
	'ADMIN,TRUSTED_CLIENT',
	3600,
	2592000
);

INSERT INTO oauth_client_details(
  CLIENT_ID,
  RESOURCE_IDS,
  CLIENT_SECRET,
  SCOPE,
  AUTHORIZED_GRANT_TYPES,
  AUTHORITIES,
  ACCESS_TOKEN_VALIDITY,
  REFRESH_TOKEN_VALIDITY,
  WEB_SERVER_REDIRECT_URI
) VALUES (
  'base-app-web',
  'base-app-resource',
  '$2a$10$gfBHWYa6muZQJEsxgJchvu5gJZPV4lmUoMPGxr0SoRZypMfv86iMC',
  'read,write',
  'authorization_code,refresh_token',
  'ADMIN,TRUSTED_CLIENT',
  3600,
  2592000,
  'http://localhost:8101'
);

INSERT INTO oauth_client_details(
  CLIENT_ID,
  RESOURCE_IDS,
  CLIENT_SECRET,
  SCOPE,
  AUTHORIZED_GRANT_TYPES,
  AUTHORITIES,
  ACCESS_TOKEN_VALIDITY,
  REFRESH_TOKEN_VALIDITY,
  WEB_SERVER_REDIRECT_URI
) VALUES (
  'base-app-dev',
  'base-app-resource',
  '$2a$10$gfBHWYa6muZQJEsxgJchvu5gJZPV4lmUoMPGxr0SoRZypMfv86iMC',
  'read,write',
  'authorization_code,refresh_token',
  'ADMIN,TRUSTED_CLIENT',
  3600,
  2592000,
  'http://localhost:8080,http://localhost:8080/webjars/springfox-swagger-ui/oauth2-redirect.html'
);

insert into users values (
  'wkOVvilFg4Tgp78U4tkjSYlrqvm1',
  'admin',
  'admin@global.techhub',
  '$2a$10$AhYQrEQ9YEaNzfGGJ7VLoO7iO5mKVyd.I8OLv2Ihz8lavwb6Bw.iy',
  '',
  true,
  true,
  true,
  true
);

insert into roles values (
  '1',
  'ADMIN'
);

insert into roles values (
  '2',
  'USER'
);

insert into user_role values (
  'wkOVvilFg4Tgp78U4tkjSYlrqvm1',
  '1'
);
