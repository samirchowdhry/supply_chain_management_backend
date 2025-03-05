package com.example.supplychainsystem.Service;

import com.example.supplychainsystem.Model.UserInfo;
import com.example.supplychainsystem.Model.UserInfoDetails;
import com.example.supplychainsystem.Repository.UserInfoRepository;
import com.example.supplychainsystem.RequestDTO.UserRequestDTO;
import com.example.supplychainsystem.ResponseDTO.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepositoryRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userDetail = userInfoRepositoryRepository.findByUsername(username);
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    public UserResponseDTO addUser(UserRequestDTO userRequestDTO) {
        Optional<UserInfo> userDetail = userInfoRepositoryRepository.findByUsername(userRequestDTO.getUsername());
        if(userDetail.isEmpty()){
            UserInfo userInfo = new UserInfo();
            userInfo.setDisplayName(userRequestDTO.getDisplayName());
            userInfo.setUsername(userRequestDTO.getUsername());
            userInfo.setRoles(userRequestDTO.getRoles());
            userInfo.setPassword(encoder.encode(userRequestDTO.getPassword()));
            UserInfo user = userInfoRepositoryRepository.save(userInfo);
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setId(user.getId());
            userResponseDTO.setUsername(user.getUsername());
            userResponseDTO.setDisplayName(user.getDisplayName());
            userResponseDTO.setPassword(user.getPassword());
            userResponseDTO.setRoles(user.getRoles());
            userResponseDTO.setCreatedAt(user.getCreatedAt());
            userResponseDTO.setUpdatedAt(user.getUpdatedAt());
            return userResponseDTO;
        }
        else{
            return null;
        }
    }

    public List<UserResponseDTO> getAllUsers(){
        List<UserInfo> userInfoList = userInfoRepositoryRepository.findAll();
        List<UserResponseDTO> userResponseDTOList = new ArrayList<>();
        for (UserInfo user:userInfoList) {
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setId(user.getId());
            userResponseDTO.setUsername(user.getUsername());
            userResponseDTO.setDisplayName(user.getDisplayName());
            userResponseDTO.setPassword(user.getPassword());
            userResponseDTO.setRoles(user.getRoles());
            userResponseDTO.setCreatedAt(user.getCreatedAt());
            userResponseDTO.setUpdatedAt(user.getUpdatedAt());
            userResponseDTOList.add(userResponseDTO);
        }

        if (userResponseDTOList.size() > 0){
            return userResponseDTOList;
        }
        else{
            return null;
        }
    }

    public UserResponseDTO getUserByEmail(String username){
        UserInfo userDetail = userInfoRepositoryRepository.findByEmail(username);

            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setId(userDetail.getId());
            userResponseDTO.setUsername(userDetail.getUsername());
            userResponseDTO.setDisplayName(userDetail.getDisplayName());
            userResponseDTO.setPassword(userDetail.getPassword());
            userResponseDTO.setRoles(userDetail.getRoles());
            userResponseDTO.setCreatedAt(userDetail.getCreatedAt());
            userResponseDTO.setUpdatedAt(userDetail.getUpdatedAt());
            return userResponseDTO;

    }

}

