import { Component, OnInit } from '@angular/core';
import { ReceipientserviceService } from '../receipientservice.service';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  editReceiverData:any=[];


  constructor(private ReceiverService:ReceipientserviceService) { }

  ngOnInit() {
    this.ReceiverService.getRecipient("na123").subscribe(data=>{
      this.editReceiverData=data;
      console.log(this.editReceiverData);
      }
      );
      

    }
  
    id=new FormControl();

  editReceipient() {
    console.log(this.editReceiverData);
    this.ReceiverService.saveReceipient(this.editReceiverData)
    .subscribe(
      response => console.log(response),
      error => console.log(error)
    )
  }

  onItemChange(event){
    console.log(event.target.value);
  }
}
