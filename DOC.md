**Идея решения:** я решил измерять скорость следущим образом: 
во-первых, нужно учесть, что скорости при чтении/записи последовательных данных
и маленьких блоков данных, находящихся в разных частях диска, различаются довольно сильно,
поэтому эти скорости лучше измерять отдельно. 

Для измерения последовательной скорости я 
генерирую файл размером _100 MB_, записываю его на диск, а потом считываю обратно. 

Для измерения скорости чтения маленьких блоков данных я выбираю некоторое количество случайных файлов на диске, 
размер которых хотя бы _4 KB_ и считываю первые _4 KB_ из них. 

Для измерения скорости записи маленьких блоков данных я выбираю некоторое количество
случайных папок, а также файл размером _4 KB_ и создаю в каждой из выбранных папок
файл, в который записываю сгенерированный текст. 
  
Чтобы исключить различные флуктуации и в целом повысить точность измерений, каждый тест я запускаю по пять раз 
и ответ усредняю.   

**Полученные результаты:** 

Скорость чтения маленьких блоков данных из случайных мест диска: 44 МБ / с

Скорость записи маленьких блоков данных в случайные места диска: 67 МБ / с

Скорость последовательного чтения: 394 МБ / с

Скорость последовательной записи: 198 МБ / с

То, что последовательные чтение и запись происходят быстрее, довольно логично, 
так как в этом случае хорошо работает кэширование. 
