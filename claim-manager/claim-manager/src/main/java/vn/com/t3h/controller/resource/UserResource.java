package vn.com.t3h.controller.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.t3h.service.UserService;
import vn.com.t3h.service.dto.UserDTO;
import vn.com.t3h.service.dto.response.BaseResponse;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserResource {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<UserDTO>>> getAllUsers(@RequestParam(required = false) String code,
                                                                   @RequestParam(required = false) LocalDateTime fromDate,
                                                                   @RequestParam(required = false) LocalDateTime toDate,
                                                                   @RequestParam(required = false) String phone,
                                                                   Pageable pageable) {
        BaseResponse<List<UserDTO>> response = userService.getAllUsers(code, fromDate, toDate, phone, pageable);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<UserDTO>> saveUser(@RequestBody UserDTO userDTO) {
        BaseResponse<UserDTO> response = userService.saveUser(userDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<BaseResponse<UserDTO>> getUserById(@PathVariable Long id) {
        BaseResponse<UserDTO> response = userService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BaseResponse<UserDTO>> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        BaseResponse<UserDTO> response = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(response);
    }
}
