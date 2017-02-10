#lang racket
(define palindrome
  (lambda (lst)
      (equal? lst (reverse lst))
       ))

(define count
  (lambda (t lst)
  (let numcount ((lst lst) (n 0))
    (if (null? lst) n
        (numcount (cdr lst)
                  (if (eq? t (car lst))
                      (+ n 1)
                      n))))))
(define duplicates
  (lambda (lst)
    (cond
      ((null? lst)#f)
      ((member (car lst) (cdr lst)) #t)
      (else
       (duplicates (cdr lst))))))

(define remove
  (lambda (t lst)
  (cond ((null? lst)
         '())
        ((equal? t (car lst))
         (remove t (cdr lst)))
        (else
         (cons (car lst) (remove t (cdr lst)))))))

(define tail-list
  (lambda (lst n)
    (cond
      ((null? lst) '())
      ((= n 0) (cons (car lst) (tail-list (cdr lst) n)))
      ((>= n (length lst)) "error out of bounds")
      (else
       (tail-list (cdr lst) (- n 1))))))
      
