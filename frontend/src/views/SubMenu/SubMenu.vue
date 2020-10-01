<template>
  <ContentWrapper>
    <div class="content-heading">
      <div>
        Sub Menu
        <small>Subtitle</small>
      </div>
    </div>

    <b-card title="Inline form" class="card-default">
      <b-form>
        <b-form-group id="input-group-1" label="Email address:" label-for="email" description="We'll never share your email with anyone else.">
          <b-form-input id="email" type="email" required placeholder="Enter email"></b-form-input>
        </b-form-group>

        <b-form-group id="password" label="Your Name:" label-for="input-2">
          <b-form-input
              id="input-2"
              v-model="form.name"
              required
              placeholder="Enter name"></b-form-input>
        </b-form-group>

      </b-form>

      <form>
        <div class="form-group">
          <label>Email address</label>
          <input class="form-control" type="email" placeholder="Enter email"/>
        </div>
        <div class="form-group">
          <label>Password</label>
          <input class="form-control" type="password" placeholder="Password"/>
        </div>
        <div class="checkbox c-checkbox">
          <label>
            <input type="checkbox" checked=""/>
            <span class="fa fa-times"></span>Check me out</label>
        </div>
        <button class="btn btn-sm btn-secondary" type="submit">Submit</button>
      </form>
      <b-card-text>
        Some quick example text to build on the card title and make up the bulk of the card's content.
      </b-card-text>

      <b-button href="#" variant="primary">Go somewhere</b-button>
    </b-card>
  </ContentWrapper>
</template>
