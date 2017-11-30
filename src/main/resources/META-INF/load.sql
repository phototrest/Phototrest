/**
 * Author:  mier
 * Created: 28-Nov-2017
 */

INSERT INTO PHOTOTREST_USER.T_USER VALUES
(1, 25, 'chris@uwaterloo.ca', 'Chris', 'male', 0, 'Liu', '123', 'mr.chris'),
(2, 24, 'daniel@uwaterloo.ca', 'Daniel', 'male', 0, 'Caccamo', '123', 'dan'),
(3, 24, 'm2ta@uwaterloo.ca', 'Mier', 'male', 0, 'Ta', '123', 'topnessman');

INSERT INTO PHOTOTREST_USER.T_PHOTO VALUES
-- In practice, MD5 and S3KEY should be the MD5 value of a file, but not file
-- name like 'photo1.jpg', as it might cause conflicts. This is only for testing
-- purpose
(1, 0,'photo1.jpg', 'photo1.jpg', 2343, '2017-06-20 19:00:00'),
(2, 0,'photo2.jpg', 'photo2.jpg', 3667, '2017-09-20 23:50:30'),
(3, 0,'photo3.jpg', 'photo3.jpg', 4821, '2017-10-07 13:50:30'),
(4, 0,'photo4.jpg', 'photo4.jpg', 1897, '2017-05-21 16:27:48'),
(5, 1,'photo5.jpg', 'photo5.jpg', 3928, '2017-09-20 21:32:38');

INSERT INTO PHOTOTREST_USER.T_TAG VALUES
(1, 'seaside'),
(2, 'sunny'),
(3, '@topnessman'),
(4, 'london'),
(5, 'park');

INSERT INTO PHOTOTREST_USER.T_TAG_PHOTO VALUES
(1, 1),
(4, 1),
(5, 1),
(1, 2),
(2, 2),
(3, 2),
(2, 3),
(4, 3),
(1, 4),
(2, 4),
(4, 4),
(5, 4),
(2, 5),
(3, 5),
(5, 5);

INSERT INTO PHOTOTREST_USER.T_TAG_USER VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 2),
(2, 3),
(3, 1),
(3, 2),
(4, 1),
(4, 3),
(5, 1),
(5, 2),
(5, 3);


INSERT INTO PHOTOTREST_USER.T_PHOTO_UPLOADED_USER VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 3),
(2, 4),
(2, 5),
(3, 5);
