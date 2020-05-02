import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/esm/Button";
import React, {Component} from "react";
import { Image} from '@material-ui/icons';
import './ManageTask.scss';
import {CONFIG} from "../../config";
import axios from "axios";
import Auth from "../../utils/Auth/Auth";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import NumericInput from 'react-numeric-input';




type Props = {
    togglePopup: Function;
    isPopupShown: any;
}

class ManageTask extends Component <Props> {

    state: { [id: string]: any; } = {
        formControls: {
            title: {
                value: ""
            },
            description: {
                value: ""
            },
            category: {
              value: ""
            },
            endDate: {
                value: new Date()
            },
            possibleNumberOfParticipants: {
                value: ''
            },
            images: []
        },
        categories: []
    };

    parseDate(date: any) {
        let year = date.getFullYear();
        let month = parseInt(date.getMonth()+1);
        let day = date.getDate();
        return year + '-' + (month < 10 ? '0' + month : month) + '-' + (day < 10 ? '0' + day : day);
    }

    componentDidMount(): void {
        const formControls = {...this.state.formControls};
        axios(CONFIG.apiServer + 'categories/').then(data => {
            this.setState({categories: data.data})
        });
    }

    onChangeHandler = (event: any, controlName: string) => {
        const formControls = {...this.state.formControls};
        const control = {...formControls[controlName]};
        control.value = (event.target as any).value;
        formControls[controlName] = control;

        this.setState({
            formControls
        });
    };

    onSubmitHandler = () => {
        let photos:any[] = [];
        this.state.formControls.images.forEach((photo: { url: any; }) => {
            photos.push(photo.url);
        });
        let data = {
            apiKey: Auth.loggedApiKey,
            title: this.state.formControls.title.value,
            description: this.state.formControls.description.value,
            photos: photos,
            status: 'done',
            priority: 'CRITICAL',
            possibleNumberOfParticipants: this.state.formControls.possibleNumberOfParticipants.value,
            endDate: this.parseDate(this.state.formControls.endDate.value)
        };
        let categoryId:any = parseInt(this.state.formControls.category.value, 10);
        if (isNaN(categoryId))
            categoryId = null;
        axios({
            url: CONFIG.apiServer + 'tasks/',
            data: data,
            method: 'post',
            params: {
                userId: Auth.loggedUserId,
                categoryId: categoryId
            }
        }).then().catch();
        this.props.togglePopup(false);
    };

    onFileChangeHandler = (files: any) => {
        let reader = new FileReader();
        let file = files[0];

        reader.onloadend = () => {
            let formControls = {...this.state.formControls};
            let control = {
                file: file,
                url: reader.result
            };
            if (formControls.images.length < 6) {
                formControls.images.push(control);
                this.setState({
                    formControls: formControls
                });
            }
        };

        reader.readAsDataURL(file)
    };

    renderPhoto = (photo: any, index:number): any => {
        return (<div key={index} className={"col-lg-2"}>
            <img key={index} style={{width: "inherit"}} src={photo} alt={""}/>
        </div>)
    };

    renderPhotos = () => {
        let photos = this.state.formControls.images;

        let HTML:any = [];
        photos.forEach((photo:any, index:number) => {
            HTML.push(this.renderPhoto(photo.url, index));
        });
      return (                    <div className={"row"}>
          {HTML} </div>);
    };
    handleDateChange(date:any) {
        const formControls = {...this.state.formControls};
        const control = {...formControls['endDate']};
        control.value = date;
        formControls['endDate'] = control;
        this.setState({
            formControls
        });
    }

    handleNumericChange(int: any) {
        const formControls = {...this.state.formControls};
        const control = {...formControls['possibleNumberOfParticipants']};
        control.value = int;
        formControls['possibleNumberOfParticipants'] = control;

        this.setState({
            formControls
        });
    }

    renderOptionsForSelect() {
        let options:any = [];
        let categories = this.state.categories;
        categories.forEach((category:any, index: number) => {
            options.push((<option value={category.id} key={index}>{category.name}</option> ));
        });
        return options;
    }

    render() {
        return (<Modal size="lg" show={this.props.isPopupShown} onHide={() => this.props.togglePopup(false)}>
                <Modal.Header closeButton>
                    <Modal.Title>Add new task</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div className={"row"}>
                        <div className={"col-lg-10"}>
                            <div className="form-group">
                                <label>Task name</label>
                                <input className="form-control" value={this.state.formControls.title.value}
                                       onChange={(event: any) => this.onChangeHandler(event, "title")}/>
                            </div>
                        </div>
                        <div className={"col-lg-2"}>
                            <div className="avatar-edit">
                                <input type="file" id="taskImageUpload"
                                       onChange={(event: any) => this.onFileChangeHandler((event.target as HTMLInputElement).files)}/>
                                <label htmlFor="taskImageUpload">
                                    <Image/>
                                </label>
                            </div>
                        </div>
                        <div className={"col-lg-4"}>
                            <div className="form-group">
                                <label>Category</label>
                                <select className="form-control" name="priority" onChange={(event: any) => this.onChangeHandler(event, "category")}>
                                    <option/>
                                    {this.renderOptionsForSelect()}
                                </select>
                            </div>
                        </div>
                        <div className={"col-lg-4"}>
                            <div className="form-group">
                                <label style={{display: 'block'}}>End date</label>
                                <DatePicker className="form-control" selected={this.state.formControls.endDate.value} onChange={(date: any) => this.handleDateChange(date)}                                         dateFormat="yyyy-MM-dd"
                                />
                            </div>
                        </div>
                        <div className={"col-lg-4"}>
                            <div className="form-group">
                                <label>Participants</label>
                                <NumericInput className="form-control" onChange={(numeric: any) => this.handleNumericChange(numeric)}
                                />
                            </div>
                        </div>
                        <div className={"col-lg-12"}>
                            <div className="form-group">
                                <label>Task description</label>
                                <textarea style={{"height": "150px"}} className="form-control"
                                          value={this.state.formControls.description.value}
                                          onChange={(event: any) => this.onChangeHandler(event, "description")}  />
                            </div>
                        </div>
                    </div>
                    {this.renderPhotos()}
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="danger" onClick={() => this.props.togglePopup(false)}>
                        Close
                    </Button>
                    <Button variant="success" onClick={() => this.onSubmitHandler()}>
                        Create
                    </Button>
                </Modal.Footer>
            </Modal>
        );
    }

}

export default ManageTask;