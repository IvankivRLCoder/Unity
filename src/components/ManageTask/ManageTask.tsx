import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/esm/Button";
import React, {Component, FormEvent} from "react";
import avatar from '../Task/task.svg';

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
            mainImage: {
                file: "",
                url: avatar
            }
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
        console.log({
           title: this.state.formControls.title.value,
           description: this.state.formControls.description.value
        });
        this.props.togglePopup(false);
    };

    onFileChangeHandler = (files: any) => {
        let reader = new FileReader();
        let file = files[0];

        reader.onloadend = () => {
            const formControls = {...this.state.formControls};
            const control = {...formControls.mainImage};
            control.file = file;
            control.url = reader.result;
            formControls.mainImage = control;
            this.setState({
                formControls: formControls
            });
        };

        reader.readAsDataURL(file)
    };

    render() {

        return (<Modal size="lg" show={this.props.isPopupShown} onHide={() => this.props.togglePopup(false)}>
                <Modal.Header closeButton>
                    <Modal.Title>Add new task</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div className={"row"}>
                        <div className={"col-lg-4"}>
                            <img src={this.state.formControls.mainImage.url} style={{"width": "70%"}}/>
                            <input style={{"paddingTop": "10px"}} type={"file"}
                                   onChange ={(event: FormEvent<HTMLInputElement>) => this.onFileChangeHandler((event.target as HTMLInputElement).files)}/>
                            <div className={"row"} style={{"paddingTop": "10px"}}>
                                <div className={"col-lg-4"}>
                                    <img src={avatar} style={{"width": "100%"}}/>
                                </div>
                                <div className={"col-lg-4"}>
                                    <img src={avatar} style={{"width": "100%"}}/>
                                </div>
                                <div className={"col-lg-4"}>
                                    <img src={avatar} style={{"width": "100%"}}/>
                                </div>
                            </div>
                        </div>
                        <div className={"col-lg-8"}>
                            <div className="form-group">
                                <label>Task name</label>
                                <input className="form-control" value={this.state.formControls.title.value}
                                       onChange={(event: FormEvent<HTMLInputElement>) => this.onChangeHandler(event, "title")}/>
                            </div>
                            <div className="form-group">
                                <label>Task description</label>
                                <textarea style={{"height": "150px"}} className="form-control"
                                          value={this.state.formControls.description.value}
                                          onChange={(event: FormEvent<HTMLTextAreaElement>) => this.onChangeHandler(event, "description")}  />
                            </div>
                        </div>
                    </div>
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