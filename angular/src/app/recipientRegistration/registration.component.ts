import { Component, OnInit } from '@angular/core';
import { ReceipientserviceService } from '../receipientservice.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  registerReceiverData = {}

  constructor(private ReceiverService:ReceipientserviceService) { }

  ngOnInit() {
  }


  registerReceipient() {
    console.log(this.registerReceiverData);
    this.ReceiverService.saveReceipient(this.registerReceiverData)
    .subscribe(
      response => console.log(response),
      error => console.log(error)
    )
  }

  onItemChange(event){
    console.log(event.target.value);
  }

}
