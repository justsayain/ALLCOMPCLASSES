#lang racket
(define nextchar '())
(define pos 0)
(define tokenList '())
(define syntaxError
   (lambda ()
     (newline)
      (raise-user-error 
            "Syntax error at token position: "     (-    pos    1) 
             "with next character: "     nextchar )))

(define removeSpaces
  (lambda (lst)
    (cond 
      ((null? lst) '())
      ((eqv? (car lst) #\space) (removeSpaces(cdr lst)))
      (else
       (cons (car lst) (removeSpaces (cdr lst)))))))


(define getChar
  (lambda (lst)
    (newline)
    (cond
      ((< pos (length lst)) (set! nextchar (list-ref lst pos)) (display "getChar: ")(display pos)(display " ")(display nextchar)(set! pos (+ pos 1)))
      (else 
       (syntaxError)
      ))))


(define match
  (lambda (char)
   (cond
     ((eqv? nextchar char)
      (cond 
        ((not(eqv? char #\$)) (getChar tokenList))))
      (else
       syntaxError))))
(define S
  (lambda ()
    (E)
    (match #\$)))
(define E
  (lambda ()
    (T)
    (cond
      ((eqv? nextchar #\+) (match #\+)(E))
      ((eqv? nextchar #\-) (match #\-)(E))
      )))
(define T
  (lambda ()
    (F)
    (cond
      ((eqv? nextchar #\*) (match #\*)(T))
      ((eqv? nextchar #\/) (match #\/)(T)))))
(define F
  (lambda ()
    (cond
      ((or (char-alphabetic? nextchar) (char-numeric? nextchar)) (match nextchar))
      ((eqv? nextchar #\() (match #\()(E) (match #\)))
      (else
       (syntaxError)))))
(define end
  (lambda ()
    (set! tokenList '())
    (set! pos 0)
    (set! nextchar '())
    ))

(define start
  (lambda ()
    (newline)
    (getChar tokenList)
    (S)
    (cond 
      ((eq? pos (length tokenList)) (display "succesful parse")(end))
     (else
      (syntaxError)
      (display "unsuccessful parse")
      (end)
      )
     )    
))

(define parse 
  (lambda  (inputstring)
     (set!    tokenList   (removeSpaces ( string->list     inputstring)  ) )
;display the tokenlist
      (display "tokenList: ")(display tokenList)
      (start)  )  )
  
