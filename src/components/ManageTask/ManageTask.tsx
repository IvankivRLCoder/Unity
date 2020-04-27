import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/esm/Button";
import React, {Component, FormEvent} from "react";
import { Image} from '@material-ui/icons';
import './ManageTask.scss';
import {CONFIG} from "../../config";
import axios from "axios";
import Auth from "../../utils/Auth/Auth";


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
            images: []
        }
    };

    onChangeHandler = (event: FormEvent<HTMLElement>, controlName: string) => {
        const formControls = {...this.state.formControls};
        const control = {...formControls[controlName]};
        control.value = (event.target as HTMLInputElement).value;
        formControls[controlName] = control;

        this.setState({
            formControls
        });
    };

    onSubmitHandler = () => {
        console.log(this.state)
        console.log({
           title: this.state.formControls.title.value,
           description: this.state.formControls.description.value
        });
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
            priority: 'critical',
            possibleNumberOfParticipants: 10,
            endDate: '2019-04-03'
        };
        axios({
            url: CONFIG.apiServer + 'tasks',
            data: data,
            method: 'post',
            params: {
                userId: Auth.loggedUserId
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

    renderPhoto = (photo: any): any => {
        return (<div className={"col-lg-2"}>
            <img style={{width: "inherit"}} src={photo} alt={""}/>
        </div>)
    };

    renderPhotos = () => {
        let photos = this.state.formControls.images;

        let HTML:any = [];
        photos.forEach((photo:any) => {
            HTML.push(this.renderPhoto(photo.url));
        });
      return (                    <div className={"row"}>
          {HTML} </div>);
    };


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
                                       onChange={(event: FormEvent<HTMLInputElement>) => this.onChangeHandler(event, "title")}/>
                            </div>
                        </div>
                        <div className={"col-lg-2"}>
                            <div className="avatar-edit">
                                <input type="file" id="taskImageUpload"
                                       onChange={(event: FormEvent<HTMLInputElement>) => this.onFileChangeHandler((event.target as HTMLInputElement).files)}/>
                                <label htmlFor="taskImageUpload">
                                    <Image/>
                                </label>
                            </div>
                        </div>
                        <div className={"col-lg-12"}>
                            <div className="form-group">
                                <label>Task description</label>
                                <textarea style={{"height": "150px"}} className="form-control"
                                          value={this.state.formControls.description.value}
                                          onChange={(event: FormEvent<HTMLTextAreaElement>) => this.onChangeHandler(event, "description")}  />
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