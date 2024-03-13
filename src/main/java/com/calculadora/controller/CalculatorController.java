package com.calculadora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.calculadora.entities.*;
import com.calculadora.repositories.*;

@RestController
@RequestMapping("/api")
public class CalculatorController {

    @Autowired
    private OperationRepository operationRepository;

    @PostMapping("/calculate")
    public Operation calculate(@RequestBody CalculatorRequest request) {
        String expression = request.getFirstNumber() + " " + request.getOperator() + " " + request.getSecondNumber();
        Operation operation = new Operation();
        operation.setFirstNumber(request.getFirstNumber());
        operation.setOperator(request.getOperator());
        operation.setSecondNumber(request.getSecondNumber());
        operation.setExpression(expression);

        try {
            float result = performCalculation(request.getFirstNumber(), request.getOperator(),
                    request.getSecondNumber());
            operation.setResult(result);
        } catch (ArithmeticException e) {
            operation.setResult(Float.NaN);
        }

        return operationRepository.save(operation);
    }

    private float performCalculation(float firstNumber, String operator, float secondNumber)
            throws ArithmeticException {
        switch (operator) {
            case "+":
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "*":
                return firstNumber * secondNumber;
            case "/":
                if (secondNumber == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return firstNumber / secondNumber;
            default:
                throw new UnsupportedOperationException("Unsupported operator: " + operator);
        }
    }
}