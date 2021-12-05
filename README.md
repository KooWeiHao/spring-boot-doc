# Document Backend Application
> _Just a random project with [Spring Boot], [PostgreSQL], [Docker] & [Heroku]_

## Modules
| Module | Description |
| ------ | ------ |
| eric-koo-spring-boot-doc-api | store entities and services, with no implementation / business logic |
| eric-koo-spring-boot-doc-core | for implementation, business logic |
| eric-koo-spring-boot-doc-rest | as the 'start' of backend application / server side |

## Tech Stack
| Subject | Description |
| ------ | ------ |
| Backend | Spring Boot(2.2.6.RELEASE), Java 8, Maven, Spring JPA/Hibernate, Docker |
| Database | PostgreSQL |
| Cloud Platform | Heroku |

## API
Server: https://eric-koo-spring-boot-doc.herokuapp.com/eric-koo
| Path | Description | Method | Accept | Request | Response |
| ------ | ------ | ------ | ------ | ------ | ------ |
| /doc-rest/upload | To upload document | POST | form-data | file: {YOUR_SELECTED_FILE} | {"Name": "{DOCUMENT_NAME}", "Type": "{DOCUMENT_TYPE}", "Size": {DOCUMENT_SIZE}, "Preview URL": "{DOCUMENT_PREVIEW_URL}", "Download URL": "{DOCUMENT_DOWNLOAD_URL}"} |
| /doc-rest/preview/{UUID} | To preview document | GET | - | - | byte data of the document |
| /doc-rest/download/{UUID} | To download document | GET | - | - | byte data of the document |

```sh
It does not matter how slowly you go, as long as you do not stop. - Confucius
```

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

[Spring Boot]: <https://spring.io/projects/spring-boot>
[PostgreSQL]: <https://www.postgresql.org>
[Heroku]: <https://www.heroku.com>
[Docker]: <https://www.docker.com>