package cu.edu.cujae.touristpacks.dto;

public class OtherServiceContractDto extends ContractDto {

	private int idOtherServiceContract;
	private String contractDescription;
	private double costPerPax;
	private ServiceTypeDto serviceType;
	private ProvinceDto province;

	public OtherServiceContractDto() {
	}

	public OtherServiceContractDto(int idOtherServiceContract, String contractDescription, double costPerPax,
			ServiceTypeDto serviceType, ProvinceDto province) {
		this.idOtherServiceContract = idOtherServiceContract;
		this.contractDescription = contractDescription;
		this.costPerPax = costPerPax;
		this.serviceType = serviceType;
		this.province = province;
	}

	public int getIdOtherServiceContract() {
		return this.idOtherServiceContract;
	}

	public void setIdOtherServiceContract(int idOtherServiceContract) {
		this.idOtherServiceContract = idOtherServiceContract;
	}

	public String getContractDescription() {
		return this.contractDescription;
	}

	public void setContractDescription(String contractDescription) {
		this.contractDescription = contractDescription;
	}

	public double getCostPerPax() {
		return this.costPerPax;
	}

	public void setCostPerPax(double costPerPax) {
		this.costPerPax = costPerPax;
	}

	public ServiceTypeDto getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(ServiceTypeDto serviceType) {
		this.serviceType = serviceType;
	}

	public ProvinceDto getProvince() {
		return this.province;
	}

	public void setProvince(ProvinceDto province) {
		this.province = province;
	}

}
