INSERT INTO `customers` (id, name) VALUES (1, 'Mugurel'), (2, 'John');

INSERT INTO `phones` (id, type, number, activated, customer_id) VALUES (1, 'home', '(201) 655-9764', true, 1), (2, 'mobile', '(999) 367-9571', true, 1), (3, 'work', '(757) 319-6764', false, 1), (4, 'home', '(529) 916-6167', false, 2), (5, 'work', '(762) 513-2000', true, 2);
