package com.chitkara.bfhl.service.impl;

import com.chitkara.bfhl.dto.BfhlRequest;
import com.chitkara.bfhl.dto.BfhlResponse;
import com.chitkara.bfhl.service.BfhlService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    @Value("${bfhl.full-name}")
    private String fullName;

    @Value("${bfhl.birth-date}")
    private String birthDate;

    @Value("${bfhl.email}")
    private String email;

    @Value("${bfhl.roll-number}")
    private String rollNumber;

    @Override
    public BfhlResponse processData(BfhlRequest request) {
        List<String> odd = new ArrayList<>();
        List<String> even = new ArrayList<>();
        List<String> alpha = new ArrayList<>();
        List<String> special = new ArrayList<>();

        BigInteger total = BigInteger.ZERO;
        StringBuilder allLetters = new StringBuilder();

        for (String s : request.getData()) {
            if (s == null || s.isEmpty()) {
                continue;
            }

            if (s.matches("-?\\d+")) {
                BigInteger num = new BigInteger(s);
                total = total.add(num);

                if (num.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
                    even.add(s);
                } else {
                    odd.add(s);
                }
            } else if (s.matches("[a-zA-Z]+")) {
                alpha.add(s.toUpperCase());
                allLetters.append(s);
            } else {
                special.add(s);
            }
        }

        BfhlResponse response = new BfhlResponse();
        response.setSuccess(true);
        response.setUserId(makeUserId());
        response.setEmail(email);
        response.setRollNumber(rollNumber);
        response.setOddNumbers(odd);
        response.setEvenNumbers(even);
        response.setAlphabets(alpha);
        response.setSpecialCharacters(special);
        response.setSum(total.toString());
        response.setConcatString(makeConcatString(allLetters.toString()));

        return response;
    }

    private String makeUserId() {
        String name = fullName == null ? "" : fullName.trim().toLowerCase().replace(" ", "_");
        String dob = birthDate == null ? "" : birthDate.trim();
        return name + "_" + dob;
    }

    private String makeConcatString(String str) {
        StringBuilder ans = new StringBuilder();
        StringBuilder rev = new StringBuilder(str).reverse();

        for (int i = 0; i < rev.length(); i++) {
            char ch = rev.charAt(i);
            if (i % 2 == 0) {
                ans.append(Character.toUpperCase(ch));
            } else {
                ans.append(Character.toLowerCase(ch));
            }
        }
        return ans.toString();
    }
}
