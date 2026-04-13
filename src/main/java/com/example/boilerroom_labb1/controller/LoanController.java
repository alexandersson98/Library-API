package com.example.boilerroom_labb1.controller;


import com.example.boilerroom_labb1.dto.loan.LoanRequestDto;
import com.example.boilerroom_labb1.dto.loan.LoanResponseDto;
import com.example.boilerroom_labb1.entity.Loan;
import com.example.boilerroom_labb1.openapi.BadRequestResponse;
import com.example.boilerroom_labb1.openapi.ConflictResponse;
import com.example.boilerroom_labb1.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/v1/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }


    @Operation(summary = "Create loan",
    description = "Create loan and add to database")
    @ApiResponse(responseCode = "201", description = "Created")
    @BadRequestResponse
    @ConflictResponse
    @PostMapping
    public ResponseEntity<LoanResponseDto>createLoan(@RequestBody LoanRequestDto request){
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.createLoan(request));
    }


    @Operation(summary = "Get all active loans",
    description = "Returns a list of all active loans")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<LoanResponseDto>>getActiveLoans(){
        return ResponseEntity.ok(loanService.getAllLoans());
    }

}
