import React from 'react';
import './App.css';

class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      newItem: "",
      list: []
    };
    this.getList();
  }

  async getList() {
    const response = await fetch("http://localhost:8080/Todo/1", {
      method: "GET",
      headers: { 'Content-Type': 'application/json' }
    });


    const list = await response.json();

    const array = [];

    for (const obj in list) {
      array.push({
        id: list[obj]["id"],
        title: list[obj]["title"],
        isComplete: list[obj]["complete"]
      });
    }

    console.log(array);

    this.setState({
      list: array.reverse()
    });

  }

  async changeItem(item) {

    const response = await fetch(
      "http://localhost:8080/Todo/1/" + item.id + "?isComplete=" + !item.isComplete
      , {
        method: "POST",
        headers: { 'Content-Type': 'application/json' }
      });

    if (response.status === 200) {
      return true;
    }

    return false;
  }

  async addItem(todoValue) {


    if (todoValue !== "") {

      const send = {
        title: todoValue
      }

      const response = await fetch("http://localhost:8080/Todo/1", {
        method: "POST",
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(send)
      });

      // if created then add into list
      if (response["status"] === 201) {
        const data = await response.json();

        console.log(data);

        const tempList = this.state.list;
        tempList.unshift(data);

        this.setState({
          list: tempList,
          newItem: ""
        });
      }

    }
  }

  async deleteItem(id) {
    const response = await fetch(`http://localhost:8080/Todo/1/${id}`, {
      method: "DELETE",
      headers: { 'Content-Type': 'application/json' }
    });

    if (response.status === 200) {
      const list = [...this.state.list];
      const updatedList = list.filter(item => item.id !== id);
      this.setState({
        list: updatedList
      });
    }



  }

  updateInput(input) {
    this.setState({
      newItem: input
    })

  }

  render() {
    return (
      <div>
        <h1 className="app-title"> Todo App</h1>
        <div className="container">
          Add an item...
          <br />
          <input
            type="text"
            className="input-text"
            placeholder="Write todo.."
            required
            // value is ""
            value={this.state.newItem}
            onChange={e => this.updateInput(e.target.value)}
          />
          <button
            className="add-btn"
            onClick={() => this.addItem(this.state.newItem)}
            disabled={!this.state.newItem.length}
          >Add Todo</button>
          <br />
          <div className="list">
            <ul>

              {this.state.list.map(item => {
                return (
                  <li key={item.id}>
                    <input
                      type="checkbox"
                      name="idDone"
                      defaultChecked={item.isComplete}
                      onChange={e => {
                        if (this.changeItem(item)) {
                          console.log(item.isComplete)
                          item.isComplete = !item.isComplete;
                          // console.log(item.isComplete)
                          e.target.checked = item.isComplete;
                        }

                      }}
                    />
                    {item.title}
                    <button
                      className="btn"
                      onClick={() => this.deleteItem(item.id)}
                    >Delete</button>
                  </li>
                );
              })}
              
            </ul>
          </div>
        </div>
      </div>
    );
  }
}

export default App;