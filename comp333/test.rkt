#lang racket
(display "You guys are da wurst")
(define coeff 
  (lambda (lst t) 
    (cond 
      ((null? lst)
       "not here"
       )
      ((equal? t (car (car lst)))
       (car (car lst))
       )
      (else (coeff (cdr lst) t)))))
(define expon 
  (lambda (lst t)
    (cond 
      ((null? lst) "none of it")
      ((equal? t (car (car lst)))
       (car(cdr(car lst)))
       )
      (else (expon (cdr lst) t)))))
(define printTerm
  (lambda (lst t)
    (cond 
      ((null? lst) "nope stahp it")
      ((equal? 0 (coeff lst t)) (display "0"))
      ((equal? 0 (expon lst t)) (display (coeff lst t)))
      ((equal? 1 (expon lst t)) (display (coeff lst t))(display "x"))
      ((equal? t (coeff lst t)) (display (coeff lst t)) (display "x^")(display (expon lst t)))  
       (else (printTerm (cdr lst) t)))))

(define printPoly
  (lambda (lst)
    (cond
      ((null? lst) "")
      
      (else 
       (display (printTerm lst(car(car lst))))      
       (display "+")
       (printPoly (cdr lst) )))))
(define evalPoly
  (lambda (lst x)
    (cond
       ((null? lst) 1)
       ((= x 0) 0)
       
       (else
        (+ (* (coeff lst (car (car lst))) (expt x (expon lst (car (car lst)))))
           (evalPoly (cdr lst) x))))))


(newline)
(coeff '((2 3)(3 4)( 4 5)) 4)
(expon '((2 3)(3 4)( 4 5)) 4)
(printTerm '((2 3)(3 4)( 4 5)) 4)
(newline)
(printPoly '((2 3)(3 4)( 4 5)))
(expt 2 3)
(+ (* (coeff '((2 3)(3 4)( 4 5)) 4) (expon '((2 3)(3 4)( 4 5)) 4)) (coeff '((2 3)(3 4)( 4 5)) 4)) 
(evalPoly '((2 3)(3 4))1)
(printTerm '((2 3)(3 1)( 0 5)) 3)
(define p1 '( ( 3 2) (8 3) (7 1) (4 0)))
(define p2 '( ( 5 2) (6 4) (-9  3) (4 0)))
(define p3 '(( 3 2) (-8 1)  ( 5 2 ) ( 6 1) ( 6 2)))
(define p4 '( (5 0) ( 2 3)  ( -3 1) ))
(define p5 '( (2 1) ( 1 0)))

(printPoly p3)

        