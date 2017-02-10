#lang racket
(define snap
  (lambda (lst1 lst2)
    (if( or (null? lst1) (null? lst2))
       '()
       (cons (+ (car lst1)(car lst2))
             (snap (cdr lst1)(cdr lst2))))))