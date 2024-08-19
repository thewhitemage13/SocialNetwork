package org.thewhitemage13.service;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thewhitemage13.exception.IncorrectPhoneNumberException;
import org.thewhitemage13.interfaces.PhoneValidationServiceInterface;
import org.thewhitemage13.repository.UserRepository;

@Service
public class PhoneValidationService implements PhoneValidationServiceInterface {
    private final UserRepository userRepository;
    private final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    @Autowired
    public PhoneValidationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String validateUpdatePhoneNumber(String phoneNum, String region) throws NumberParseException {
        return getString(phoneNum, region);
    }

    @Override
    public String validatePhoneNumber(String phoneNum, String region) throws NumberParseException {
        if (userRepository.existsUserByPhoneNumber(phoneNum)){
            throw new IncorrectPhoneNumberException("Phone number is already in use");
        }else {
            return getString(phoneNum, region);
        }
    }

    private String getString(String phoneNum, String region) throws NumberParseException {
        String formattedNumber;

        Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(phoneNum, region);

        boolean isValid = phoneNumberUtil.isValidNumber(phoneNumber);

        if (!isValid) {
            throw new IncorrectPhoneNumberException("Incorrect phone number");
        }else {
            formattedNumber = phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
        }
        return formattedNumber;
    }
}
