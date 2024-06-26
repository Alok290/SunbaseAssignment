package com.example.SunbaseAssignment.Controller;


import com.example.SunbaseAssignment.Dtos.RequestDTo.AuthRequestDto;
import com.example.SunbaseAssignment.Dtos.RequestDTo.UserRequestDto;
import com.example.SunbaseAssignment.Dtos.ResponseDto.AuthResponseDto;
import com.example.SunbaseAssignment.Enum.AuthStatus;
import com.example.SunbaseAssignment.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class authController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthRequestDto authRequestDto){

    var jwtToken = authService.login(authRequestDto.getUsername(),authRequestDto.getPassword());

    var Result = new AuthResponseDto(jwtToken, AuthStatus.LOGIN_SUCCESS);

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(Result);

    }


    @PostMapping("/sign-up")
    public ResponseEntity Signup(@RequestBody UserRequestDto userRequestDto) throws Exception{

        try {
            var jwtToken =authService.signup(userRequestDto);

            var Result = new AuthResponseDto(jwtToken, AuthStatus.USER_CREATED_SUCCESSFULLY);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Result);
        }
        catch (Exception e) {

            var Result = new AuthResponseDto(null, AuthStatus.USER_NOT_CREATED);

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Result);

        }
    }

}
