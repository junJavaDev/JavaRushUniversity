-- Write your code here:
SELECT city
    FROM cities
ORDER BY CHAR_LENGTH(city) DESC
LIMIT 3