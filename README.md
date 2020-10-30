# RepositoryWatcher
1. Приложение слушает директорию на появление новых файлов:
        - При появлении нового файла, в лог выводится его имя, его расширение и дата создания
        - После, в новом потоке запускается обработка нового файла, выбор обработчика осуществляется в зависимости
          от расширения
        - Обработчик выводит в лог данные о времени начала обработки и общее время обработки файла
        - Если файл по расширению не подходит под допустимый, запускается обработчик, который удаляет данный файл

     2. Допустимы два расширения: xml, json
     3. В качестве имитации обработки, в лог выводится количество строк в файле
     4. Логгирование осуществляется в файл на диске
