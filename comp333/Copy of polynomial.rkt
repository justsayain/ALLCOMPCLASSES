;Steve Delgado
;Luis Guzman
;Andrea Rojas
;Project 3

#lang racket
(define fact
(lambda (n)
  (if (= n 0) 1
      (* n (fact (- n 1))))))

;print the coefficient of a tuple
(define coeff 
  (lambda (t) 
    (cond 
      ((null? t) empty)
      (else
          (car t)))))

;print the exponent of the tuple
(define expon 
  (lambda (t)
    (cond 
      ((null? t) empty)
       (else
            (car(cdr t))))))

;print the tuple formatted with the x^
(define printTerm
  (lambda (t)
    (cond
       ((null? t) empty)
       ;check for power of 0
       ((equal? (expon t) 0)
                (display(coeff t)))
       ;check for power of 1
       ((equal? (expon t) 1)
                (display(coeff t))
                (display "x"))
       ;base case display the x^
       (else
            (display(coeff t))
            (display "x^")
            (display(expon t))))))

(define printPoly
  (lambda (p)
    (cond
      ((null? p) empty)
    (else
       (printTerm (car p))
       (display "+")
       (printPoly (cdr p))))))

(define evalpoly
  (lambda (p v)
    (cond
      ((null? p) empty)
      ((null? v) empty)
      (else
       (evalpoly ((cdr p) v))
    )
  )))

(define GT
  (lambda (t1 t2)
    (newline)
    )
  )

(define EQExp?
  (lambda (t1 t2)
    (newline)
    )
  )

(define simplify
  (lambda (p)
    (newline)
    )
  )

(define addpoly
  (lambda (p1 p2)
    (newline)
    )
  )

(define subtractpoly
  (lambda (p1 p2)
    (newline)
    )
  )

(define multiplyterms
  (lambda (t1 t2)
    (cond 
      ((null? t1) )
      ((null? t2) )
      (else
           (display (append (* (car t1) (car t2))))
           (multiplyterms (cdr t1) (cdr t2))))))

(define multiplytermpoly
  (lambda (t lst)
    (newline)
    )
  )

(define multiplypoly
  (lambda (p1 p2)    
    (newline)
    )
  )

(display "expon")
(newline)
(expon '(2 3))
(display "coeff")
(newline)
(coeff '(2 3))
(printTerm '(2 3))
(newline)
(display "printpoly")
(newline)
(printPoly '((2.13 3) (1.5 4) (6 3) (4 1) (-3 0)))