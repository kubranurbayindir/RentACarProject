package com.etiya.rentACarSpring.core.utilities.helpers;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.etiya.rentACarSpring.businnes.constants.FilePath;
import com.etiya.rentACarSpring.core.utilities.businnessRules.BusinnessRules;
import com.etiya.rentACarSpring.core.utilities.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccesResult;

@Service
public class ImageFileHelper implements FileHelper {

	private static String imagePath = FilePath.imagePath;

	@Override
	public Result uploadImage(int carId, MultipartFile file) throws IOException {
		var result = BusinnessRules.run(checkImageType(file));

		if (result != null) {
			return result;
		}

		String newCarFolderName = this.createCarImageFolderAndreturnCarImageFolderName(carId).getMessage(); //her car için klosor oluşturuyor.
		String newImageName = this.createImageName(file).getMessage();
		
		File myFile = new File(imagePath + newCarFolderName + "\\" + newImageName);	//dosyanın yolunu tanımlıyorsun
		myFile.createNewFile();														//dosya açılıyor
		FileOutputStream fos = new FileOutputStream(myFile);	
		fos.write(file.getBytes());													//resim dosyaya yazılıyor
		fos.close();																//dosya kapatılıyor

		return new SuccesResult(newCarFolderName + "\\" + newImageName);
	}
	@Override
	public Result updateImage(MultipartFile file, String imagePath) throws IOException {

		var result = BusinnessRules.run(checkImageType(file));
		if (result != null) {
			return result;
		}

		String CarFolderName = imagePath.substring(0, imagePath.indexOf("\\"));
		this.deleteImage(imagePath);

		String newImageName = this.createImageName(file).getMessage();
		File myFile = new File(FilePath.imagePath+CarFolderName + "\\" + newImageName);
		myFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(myFile);
		fos.write(file.getBytes());
		fos.close();

		return new SuccesResult(CarFolderName + "\\" + newImageName);
	}

	@Override
	public Result deleteImage(String imageUrl) {
		if (!imageUrl.isEmpty() && !imageUrl.isBlank()) {

			File file = new File(imagePath + imageUrl);
			file.delete();
		}

		return new SuccesResult();
	}
	
	
	@Override
	public Result checkImageType(MultipartFile file) {
		if (!checkImageIsNull(file).isSuccess()) {
			return new ErrorResult(checkImageIsNull(file).getMessage());
		}
		if (!file.getContentType().toString().substring(file.getContentType().indexOf("/") + 1).equals("jpeg")
				&& !file.getContentType().toString().substring(file.getContentType().indexOf("/") + 1).equals("jpg")
				&& !file.getContentType().toString().substring(file.getContentType().indexOf("/") + 1).equals("png")
				&& !file.getContentType().toString().substring(file.getContentType().indexOf("/") + 1).equals("gif")) { //gif için test et!!
			return new ErrorResult("Dosya uzantısı geçerli değil");
		}
		return new SuccesResult();

	}

	private Result checkImageIsNull(MultipartFile file) {		
		if (file == null || file.isEmpty() || file.getSize() == 0) {
			return new ErrorResult("Herhangi bir resim seçmediniz");
		}
		return new SuccesResult();
	}

	private Result createCarImageFolderAndreturnCarImageFolderName(int carId) {

		String newCarFolderName = "car" + carId; 		//klosör adı car+car'a ait id ile birlikte oluşturulur.

		File myFolder = new File(imagePath + newCarFolderName);  //yeni bir klosör oluşturuyor.
		myFolder.mkdir(); 										 //linux komutu
		
		return new SuccesResult(newCarFolderName);
	}

	private Result createImageName(MultipartFile file) {  //klosörün içerisine eklenen resimlerin uniq olarak kayıt olmasını saglar.
		String randomImageName = java.util.UUID.randomUUID().toString();

		String newImageName = randomImageName + "."
				+ file.getContentType().toString().substring(file.getContentType().indexOf("/") + 1);

		return new SuccesResult(newImageName);
	}



}
