# EPAM-Training
Многомодульный проект по заданиям тренинга "java core".

Основным является "курсовой проект" **http-server**.
Текущая реализация сервера поддерживает только передачу статических файлов (запросы GET и HEAD).
Поддерживается всего три типа ответов "200 OK", "404 Not Found", "501 Not Implemented".
Заголовок ответа заполняется в соответствии с передаваемым файлом. (длина, дата, MIME-type)
Архитектура сервера "блокирующая".
Возможно написание своего обработчика запросов, для этого необходимо унаследовать `SocketProcessor`.

Для тестирования использован junit 5 (junit-jupiter)

Для запуска используйте `server.HttpServer#main`

**!!! Важно:**
- При запуске сервера проверьте корректность параметров в файле ресурсаов (http-server.properties)
В частности, укажите корректную директорию расположения файлов.
- Текущая реализация не является безопасной, так как отсутствуют какие-либо ограничения на передаваемые файлы.

По всем вопросам обращайтесь:
kors.ilya@gmail.com
