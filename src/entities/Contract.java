package entities;

import enumerations.TypeOfContract;

import java.time.LocalDate;

public class Contract {
    private LocalDate contractDate;
    private String filePathContract;
    private TypeOfContract typeOfContract;

    public Contract(LocalDate contractDate, String filePathContract, TypeOfContract typeOfContract) {
        this.contractDate = contractDate;
        this.filePathContract = filePathContract;
        this.typeOfContract = typeOfContract;
    }

    public int registerContract() {
        return 0;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public String getFilePathContract() {
        return filePathContract;
    }

    public TypeOfContract getTypeOfContract() {
        return typeOfContract;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    public void setFilePathContract(String filePathContract) {
        this.filePathContract = filePathContract;
    }

    public void setTypeOfContract(TypeOfContract typeOfContract) {
        this.typeOfContract = typeOfContract;
    }
}
